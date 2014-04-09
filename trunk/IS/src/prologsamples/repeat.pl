get_next_digit(D) :-
  repeat,
  get_single_char(X), put_byte(X), X >= "0", X =< "9", name(D,[X]).