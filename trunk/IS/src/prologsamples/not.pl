neg(P) :- call(P), !, fail.
neg(_).

sleepless(X) :- 
  neg(married(X)), man(X).

man(kai). man(bernd).

married(bernd). 