% Autor:
% Datum: 03.04.2014

 % countEins(Liste,AnzahlEinsen)
 countEins([],0).
 countEins([Head|Tail],AnzahlEinsen) :-
           Head = 1,
           countEins(Tail,TAnzahlEinsen),
           AnzahlEinsen is 1 + TAnzahlEinsen.
  countEins([Head|Tail],AnzahlEinsen) :-
           Head \= 1,
           countEins(Tail,AnzahlEinsen).
 % countEinsAccu(Liste,AnzahlEinsen)
 countEinsAccu(Liste,AnzahlEinsen) :-
       countEinsAccu(Liste,0,AnzahlEinsen).

countEinsAccu([Head|Tail],Accu,AnzahlEinsen) :-
           Head = 1, NeuerAccu is 1 + Accu,
           countEinsAccu(Tail,NeuerAccu,AnzahlEinsen).
countEinsAccu([Head|Tail],Accu,AnzahlEinsen) :-
           Head \= 1,
           countEinsAccu(Tail,Accu,AnzahlEinsen).
countEinsAccu([],Accu,AnzahlEinsen) :- AnzahlEinsen = Accu.

countEinsT(Liste,AnzahlEinsen) :-
       countEinsT(Liste,0,AnzahlEinsen).
countEinsT([Head|Tail],Accu,AnzahlEinsen) :-
           Head = 1,NeuerAccu is 1 + Accu,
           countEinsT(Tail,NeuerAccu,AnzahlEinsen).
countEinsT([Head|Tail],Accu,AnzahlEinsen) :-
           %Head \= 1,
           ((is_list(Head),countEinsT(Head,HAnzEinsen));
             (not(is_list(Head)),HAnzEinsen = 0)),
           NeueAccu is Accu + HAnzEinsen,
           countEinsT(Tail,NeueAccu,AnzahlEinsen).
countEinsT([],Accu,AnzahlEinsen) :- AnzahlEinsen = Accu.


%gleich(Listeinfix,Liste2)
gleich([Head|Rest],[Head|Rest2]) :- gleich(Rest,Rest2).
gleich([],[_Head|_Rest2]).
%gleich([],_Liste).
% infix([I|RI],[I|L]) :- gleich([I|R],[I|L]) ; infix([I|R],L).

mnot(X) :- write('Eingang'),X,!,writeln('Ausgang'),fail.
mnot(_) :- writeln('Ausgang Zwei').


drucke(Name) :- elternteil(Name,X),writeln(X),fail.
drucke(_Name).
test :- drucke(ulrike),writeln('Testende').