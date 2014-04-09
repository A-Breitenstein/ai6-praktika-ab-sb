reverse([],[]).
reverse([First|Rest], Liste) :- reverse(Rest,Temp), append(Temp,[First],Liste).
