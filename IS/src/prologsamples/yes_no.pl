yes_no(yes,no).
yes_no(yes(X),no(Y)) :- yes_no(X,Y).