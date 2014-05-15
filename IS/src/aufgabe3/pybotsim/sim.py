import itertools
import random

# board: 0=empty, 1=player1, 2=player2
def newgame(boardsize = 6):
    global n, edges, board, gameend
    n = boardsize
    edges = list(itertools.combinations(xrange(n), 2))
    board = [0]*(n*(n-1)/2)
    gameend = False
    printboard()

# converts position to move
def pos2move(position):
    return edges[position]

# converts move to position
def move2pos(a, b):
    if b < a:
        a, b = b, a
    return a*(n-1) - (a*(a+1))/2 + b - 1

# returns list of free positions
def legalpositions(board):
    return [i for (i,p) in enumerate(board) if not p]

# prints current state of the board
def move2str(a, b):
    return "%d-%d" % (a,b)

def printboard():
    for i in xrange(3):
        s = [move2str(*pos2move(j)) for (j,p) in enumerate(board) if p == i]
        print "p%d: " % i + " ".join(s)

# returns whether a move at position would lose the game
def gameover(board, player, position):
    a, b = pos2move(position)
    neighbors = range(n)
    neighbors.remove(a)
    neighbors.remove(b)
    for v in neighbors:
        if board[move2pos(a, v)] == player and board[move2pos(v, b)] == player:
            return True
    return False

# take a move
def move(player, position):
    global board, gameend
    board[position] = player
    a, b = pos2move(position)
    print "player %d moves at %d-%d" % (player, a, b)
    printboard()
    if gameover(board, player, position):
        gameend = True
        print "game over. player %d lost." % player

# take a move. override: allow illegal moves
def mov(player, a, b, override=False):
    position = move2pos(a, b)
    if not override and board[position]:
        print "illegal move"
    else:
        move(player, position)

# AI that avoids losing on the very next move
def dumbo(player):
    for position in legalpositions(board):
        if not gameover(board, player, position):
            break
    move(player, position)

# simple monte carlo while avoiding losing on the very next move
def mc(player, runs=50000):
    goodpos = [pos for pos in legalpositions(board)
                   if not gameover(board, player, pos)]
    if not goodpos:
        move(player, legalpositions(board)[0])
        return None
    scores = [0]*len(goodpos)
    for i, position in enumerate(goodpos):
        for j in xrange(runs / len(goodpos)):
            plr, pos = player, position
            aiboard = list(board)
            while True:
                aiboard[pos] = plr
                plr = plr%2 + 1
                nonlpos = [pos for pos in legalpositions(aiboard)
                               if not gameover(aiboard, plr, pos)]
                if nonlpos:
                    pos = random.choice(nonlpos)
                else:
                    break
            scores[i] += (plr == player)
    position = goodpos[scores.index(min(scores))]
    scores = [100-100*len(goodpos)*s/runs for s in scores]
    scores = zip([move2str(*pos2move(pos)) for pos in goodpos], scores)
    scores.sort(key=lambda x: x[1], reverse=True)
    print "scores: " + ", ".join(['%s (%d)' % (m, s) for (m, s) in scores])
    move(player, position)

# let AI play against AI. example: demo("mc(%d,1000)", "dumbo(%d)")
def demo(*s):
    p = 0
    while not gameend:
        exec(s[p] % (p+1))
        p = (p+1) % 2
        print ""

def main():
    newgame()

if __name__ == '__main__':
    main()