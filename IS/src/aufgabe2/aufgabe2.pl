:- include(aufgabe1).

s(SemS, s(IP,VP,PP)) --> ip(SemIP,IP), vp(SemVP,VP), pp(SemVP,PP),
                         {SemVP = [_,SemIP,_], SemS =.. SemVP}.
s(SemS, s(V,E1,NP,PP)) --> v(V), e(SemE1,E1), np(SemVP,NP), pp(SemVP,PP),
                           {SemVP = [_,SemE1,_], SemS =.. SemVP}.
vp([SemNP,_,_],vp(V,NP)) --> v(V), np([SemNP,_,_],NP).
np([SemNP,_,_],np(N)) --> n(SemNP,N).
np([SemNP,_,_],np(A, N)) --> a(A), n(SemNP,N).
pp([_,_,SemE],pp(P, E2)) --> p(P), e(SemE,E2).

ip(SemIP,ip(IP)) --> [IP], {lex(IP,SemIP,ip)}.
a(a(A)) --> [A], {lex(A,a)}.
n(SemN,n(N)) --> [N], {lex(N,SemN,n)}.
p(p(P)) --> [P], {lex(P,p)}.
e(SemE,e(E)) --> [E], {lex(E,SemE,e)}.
v(v(V)) --> [V], {lex(V,v)}.


lex(peter,peter,e).
lex(ingeborg,ingeborg,e).
lex(hans,hans,e).
lex(alex,alex,e).
lex(sven,sven,e).
lex(sabine,sabine,e).
lex(laura,laura,e).
lex(alina,alina,e).
lex(anna,anna,e).
lex(berta,berta,e).
lex(brigitte,brigitte,e).
lex(charline,charline,e).
lex(christiane,christiane,e).
lex(dorothea,dorothea,e).

lex(tante,tante,n).
lex(bruder,bruder,n).
lex(vater,vater,n).
lex(verheiratet,verheiratet,n).
lex(geschwister,geschwister,n).
lex(oma,oma,n).
lex(opa,opa,n).
lex(vorfahre,vorfahre,n).
lex(schwester,schwester,n).
lex(elternteil,elternteil,n).
lex(onkel,onkel,n).
lex(cousin,cousin,n).
lex(cousine,cousine,n).
lex(schwager,schwager,n).
lex(schwaegerin,schwaegerin,n).

lex(wer,Wer,ip).

lex(ist,v).

lex(der,a).
lex(die,a).

lex(von,p).
lex(mit,p).

