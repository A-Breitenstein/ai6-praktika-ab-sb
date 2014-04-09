s(s(NP,VP))    --> np(NP), vp(VP).

np(np(DET,N))  --> det(DET), n(N).
np(np(PN))     --> pn(PN).

vp(vp(V,NP))   --> v(V), np(NP).
vp(vp(V))      --> v(V).

det(det(DET))  --> [DET], {lex(DET,det)}.

n(n(N))        --> [N],   {lex(N,n)}.

pn(pn(PN))     --> [PN],  {lex(PN,pn)}.

v(v(V))        --> [V],   {lex(V,v)}.

lex(the,det).

lex(woman,n).
lex(man,n).
lex(apple,n).
lex(blues,n).

lex(eats,v).
lex(loves,v).
lex(sings,v).

lex(paul,pn).
lex(paula,pn).
