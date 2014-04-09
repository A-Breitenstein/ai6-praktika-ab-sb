s(First,Rest)   :- np(First, Temp), vp(Temp,Rest).

np(First,Rest)  :- det(First, Temp), n(Temp,Rest).
np(First,Rest)  :- pn(First, Rest).

vp(First,Rest)  :- v(First,Temp), np(Temp,Rest).
vp(First,Rest)  :- v(First,Rest).

det([the|Rest],Rest).

n([woman|Rest],Rest).
n([man|Rest],Rest).
n([apple|Rest],Rest).
n([blues|Rest],Rest).

v([eats|Rest],Rest).
v([loves|Rest],Rest).
v([sings|Rest],Rest).

pn([paul|Rest],Rest).
pn([paula|Rest],Rest).

