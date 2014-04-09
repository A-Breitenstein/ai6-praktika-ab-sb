member1(Elem,List) :- List = [Elem|_].
member1(Elem,List) :- 
	List = [_|Rest], member1(Elem,Rest).

member2(Elem,[Elem|_]).
member2(Elem,[_|Rest]) :- member2(Elem,Rest).