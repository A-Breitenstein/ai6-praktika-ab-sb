seq_ok(N) :- N > 10, nl.
seq_ok(N) :- write('Kai von Luck'), nl, M is N+1, seq_ok(M).

seq_fail(N) :- write('Kai von Luck'), nl, M is N+1, seq_fail(M).
seq_fail(N) :- N > 10, nl.
 