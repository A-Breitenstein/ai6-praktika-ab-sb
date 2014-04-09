likes(paul,X)  :- fish(X), !, fail.
likes(paula,X) :- pasta(X), !, fail.
likes(_,_).

fish(salmon).
fish(plaice).

pasta(spagetti).

dessert(icecream).
dessert(applepie).

meat(steak).
meat(burger).

meal(X) :- fish(X).
meal(X) :- pasta(X).
meal(X) :- meat(X).
meal(X) :- dessert(X).

dinner42(Man,Woman,Meal) :- 
  meal(Meal), likes(Man,Meal), likes(Woman,Meal).

