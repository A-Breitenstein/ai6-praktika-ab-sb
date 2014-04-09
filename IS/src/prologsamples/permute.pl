permute(List, [First|Rest]) :-
  append(List1, [First|List2], List),
  append(List1, List2, List3),
  permute(List3, Rest).
permute([],[]).