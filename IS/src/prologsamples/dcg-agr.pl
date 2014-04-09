s(s(NP,VP))          --> np(NP,AGR), vp(VP,AGR). 

np(np(DET,N),AGR)    --> det(DET,AGR), n(N,AGR).
np(np(N),AGR)        --> pn(N,AGR).

vp(vp(V,NP),AGR)     --> v(V,AGR), np(NP,_).
vp(vp(V),AGR)        --> v(V,AGR).

det(det(Det),AGR)    --> [D],  {lex(D,Det,det,AGR)}.
 
n(n(Noun),agr(No,P)) --> [N],{lex(N,Noun,n,agr(No,P))}.

pn(n(Noun),agr(No,P))--> [PN],{lex(PN,Noun,pn,agr(No,P))}.

v(v(Verb),AGR)	 --> [V],{lex(V,Verb,v,AGR)}.

lex(woman, woman, n,agr(sg,3)).
lex(women, woman, n,   agr(pl,3)).
lex(man,   man,   n,   agr(sg,3)).
lex(men,   man,   n,   agr(pl,3)).
lex(apple, apple, n,   agr(sg,3)).
lex(apples,apple, n,   agr(pl,3)).
lex(blues, blues, n,   agr(_,3)).
lex(the,   the,   det, agr(_,3)).
lex(a,     a,     det, agr(sg,3)).
lex(an,    a,     det, agr(sg,3)). 
lex(eats,  eat,   v,   agr(sg,3)).
lex(eat,   eat,   v,   agr(pl,_)).
lex(loves, love,  v,   agr(sg,3)).
lex(love,  love,  v,   agr(pl,_)).
lex(sings, sing,  v,   agr(sg,3)).
lex(sing,  sing,  v,   agr(pl,_)).
lex(paul,  paul,  pn,  agr(sg,3)).
lex(paula, paula, pn,  agr(sg,3)).
