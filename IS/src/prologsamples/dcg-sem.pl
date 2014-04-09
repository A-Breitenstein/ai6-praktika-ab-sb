s(SemS,s(NP,VP))                --> np(SemN,NP,AGR), vp(SemVP,VP,AGR),
                                    {SemVP = [_,SemN|_], SemS =.. SemVP}.

np(SemN,np(DET,N),AGR)          --> det(DET,AGR), n(SemN,N,AGR).
np(SemN,np(N),AGR)              --> pn(SemN,N,AGR).

vp([SemV,_,SemNP],vp(V,NP),AGR) --> v(SemV,V,AGR), np(SemNP,NP,_).
vp([SemV,_],vp(V),AGR)          --> v(SemV,V,AGR).

det(det(Det),AGR)               --> [D],  {lex(D,Det,det,AGR)}.
 
n(Noun,n(Noun),agr(No,P))       --> [N],  {lex(N,Noun,n,agr(No,P))}.

pn(Noun,n(Noun),agr(No,P))      --> [PN], {lex(PN,Noun,pn,agr(No,P))}.

v(Verb,v(Verb),AGR)             --> [V],  {lex(V,Verb,v,AGR)}.

lex(woman, woman, n,   agr(sg,3)).
lex(women, woman, n,   agr(pl,3)).
lex(man,   man,   n,   agr(sg,3)).
lex(men,   man,   n,   agr(pl,3)).
lex(apple, apple, n,   agr(sg,3)).
lex(apples,apple, n,   agr(pl,3)).
lex(blues, blues, n,   agr(_,3)).

lex(the,   the,   det, agr(_,3)).
lex(an,    a,     det, agr(sg,3)).
lex(a,     a,     det, agr(sg,3)). 

lex(eats,  eat,   v,   agr(sg,3)).
lex(eat,   eat,   v,   agr(pl,_)).
lex(loves, love,  v,   agr(sg,3)).
lex(love,  love,  v,   agr(pl,_)).
lex(sings, sing,  v,   agr(sg,3)).
lex(sing,  sing,  v,   agr(pl,_)).

lex(paul,  paul,  pn,  agr(sg,3)).
lex(paula, paula, pn,  agr(sg,3)).
