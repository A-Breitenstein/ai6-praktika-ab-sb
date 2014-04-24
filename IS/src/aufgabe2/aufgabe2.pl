:- include(aufgabe1).

s(SemS, s(IP,VP,PP)) --> ip(SemIP,IP), vp(SemVP,VP), pp(SemVP,PP),
                         {SemVP = [_,SemIP,_], SemS =.. SemVP}.
s(SemS, s(V,E1,NP,PP)) --> v(V), e(SemE1,E1), np(SemVP,NP), pp(SemVP,PP),
                           {SemVP = [_,SemE1,_], SemS =.. SemVP}.


a(SemA,a(SU1,V,PRAE,P,SU2)) --> {SemA =.. [PRAE,SU1,SU2]},subj(SU1),v(V),praedikat(PRAE),p(P),subj(SU2).

praedikat(PRAE) --> art(A,AGR), n(PRAE,N,AGR).
praedikat(PRAE) --> n(PRAE,N,_).

subj(SU) --> [SU],{lex(SU,SU,e,_)}.


vp([SemNP,_,_],vp(V,NP)) --> v(V), np([SemNP,_,_],NP).
np([SemNP,_,_],np(N)) --> n(SemNP,N,AGR).
np([SemNP,_,_],np(A, N)) --> art(A,AGR), n(SemNP,N,AGR).
pp([_,_,SemE],pp(P, E2)) --> p(P), e(SemE,E2).

ip(SemIP,ip(IP)) --> [IP], {lex(IP,SemIP,ip,_)}.
art(art(A),AGR) --> [A], {lex(A,a,AGR)}.
n(SemN,n(N),AGR) --> [N], {lex(N,SemN,n,AGR)}.
p(p(P)) --> [P], {lex(P,p)}.
e(SemE,e(E)) --> [E], {lex(E,SemE,e,_)}.
v(v(V)) --> [V], {lex(V,v)}.


lex(tante,tante,n,w).
lex(bruder,bruder,n,m).
lex(vater,vater,n,m).
lex(verheiratet,verheiratet,n,_).
lex(geschwister,geschwister,n,_).
lex(oma,oma,n,w).
lex(opa,opa,n,m).
lex(vorfahre,vorfahre,n,m).
lex(schwester,schwester,n,w).
lex(elternteil,elternteil,n,m).
lex(onkel,onkel,n,m).
lex(cousin,cousin,n,m).
lex(cousine,cousine,n,w).
lex(schwager,schwager,n,m).
lex(schwaegerin,schwaegerin,n,w).
lex(W,W,T,G):- lex(W,T,G).
lex(N,N,e,_).




lex(wer,Wer,ip,_).

lex(der,a,m).
lex(die,a,w).
lex(eine,a,w).

lex(ist,v).
lex(von,p).

