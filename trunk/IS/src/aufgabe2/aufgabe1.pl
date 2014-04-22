% Autor:
% Datum: 18.03.2014

%personen
peter.
ingeborg.
hans.
alex.
sven.
sabine.
laura.
alina.
anna.
berta.
brigitte.
charline.
christiane.
dorothea.

%Geschlecht d. Personen
weiblich(ingeborg  ).
weiblich(sabine    ).
weiblich(laura     ).
weiblich(alina     ).
weiblich(anna      ).
weiblich(berta     ).
weiblich(brigitte  ).
weiblich(charline  ).
weiblich(christiane).
weiblich(dorothea  ).

maennlich(peter).
maennlich(hans).
maennlich(alex).
maennlich(sven).

% X ist Vater von Y :: vater(X,Y)
vater(peter,hans).
vater(peter,alex).
vater(peter,sven).

vater(hans,christiane).
vater(hans,dorothea).

vater(alex,brigitte).
vater(alex,anna).

vater(sven,sabine).
vater(sven,berta).

% X ist Mutter von Y :: mutter(X,Y)
mutter(ingeborg,hans).
mutter(ingeborg,alex).
mutter(ingeborg,sven).

mutter(charline,christiane).
mutter(charline,dorothea).

mutter(laura,brigitte).
mutter(laura,anna).

mutter(alina,sabine).
mutter(alina,berta).

% X ist der Ehemann von Y :: ehemann(X,Y)
ehemann(alex,laura).
ehemann(sven,alina).
ehemann(hans,charline).

verheiratet(alex,laura).
verheiratet(sven,alina).
verheiratet(hans,charline).

verheiratetA(X,Y) :- verheiratet(Y,X).
verheiratetA(X,Y) :- verheiratet(X,Y).


geschwister(X,Y):- bruder(X,Y).
geschwister(X,Y):- bruder(Y,X).
geschwister(X,Y):- schwester(X,Y).
geschwister(X,Y):- schwester(Y,X).



% X ist Oma von Y :: oma(X,Y)

oma(Oma,Kind):- vater(X,Kind),mutter(Oma,X).
oma(Oma,Kind):- mutter(X,Kind),mutter(Oma,X).

opa(Opa,Kind):- vater(X,Kind),vater(Opa,X).
opa(Opa,Kind):- mutter(X,Kind),vater(Opa,X).

% X ist vorfahre von Y  ::  vorfahre(X,Y)

vorfahre(Eltern,Person):- elternteil(Eltern,Person).
vorfahre(Vorfahre,Person):- elternteil(Eltern,Person),vorfahre(Vorfahre,Eltern).

% X ist Bruder von Y :: bruder(X,Y)
bruder(Bruder,Person):- vater(Vater,Bruder),vater(Vater,Person),maennlich(Bruder), Bruder \= Person.
bruder(Bruder,Person):- mutter(Mutter,Bruder),mutter(Mutter,Person),maennlich(Bruder), Bruder \= Person.

% X ist Schwester von Y :: schwester(X,Y)
schwester(Schwester,Person):- vater(Vater,Schwester),vater(Vater,Person),weiblich(Schwester), Schwester \= Person.
schwester(Schwester,Person):- mutter(Mutter,Schwester),mutter(Mutter,Person),weiblich(Schwester), Schwester \= Person.

% X ist Elternteil von Y
elternteil(X,Y):- vater(X,Y).
elternteil(X,Y):- mutter(X,Y).

% X ist Onkel von Y :: onkel(X,Y)
onkel(Onkel,Person):- maennlich(Onkel),elternteil(Elternteil,Person),geschwister(Onkel,Elternteil).
onkel(Onkel,Person):- maennlich(Onkel),elternteil(Elternteil,Person),geschwister(Geschwisterchen,Elternteil),verheiratetA(Onkel, Geschwisterchen).

% X ist Tante von Y :: tante(X,Y)
tante(Tante,Person):- weiblich(Tante),elternteil(Elternteil,Person),geschwister(Tante,Elternteil).
tante(Tante,Person):- weiblich(Tante),elternteil(Elternteil,Person),geschwister(Geschwisterchen,Elternteil),verheiratetA(Tante, Geschwisterchen).

% X ist Cousin von Y :: cousin(X,Y)
cousin(Cousin,Person):- maennlich(Cousin),elternteil(Elternteil_C,Cousin),elternteil(Elternteil_P,Person),geschwister(Elternteil_C,Elternteil_P).

% X ist Cousine von Y :: cousine(X,Y)
cousine(Cousine,Person):- weiblich(Cousine),elternteil(Elternteil_C,Cousine),elternteil(Elternteil_P,Person),geschwister(Elternteil_C,Elternteil_P).
                        

% Schwager ist Schwager von Person :: schwager(Schwager,Person)
schwager(Schwager,Person):- maennlich(Schwager),geschwister(Person,Geschwisterchen),verheiratetA(Schwager,Geschwisterchen).
schwager(Schwager,Person):- maennlich(Schwager),geschwister(Schwager,Geschwisterchen),verheiratetA(Person,Geschwisterchen).
schwager(Schwager,Person):- maennlich(Schwager),verheiratetA(Schwager,Ehepartner),geschwister(Ehepartner,Geschwisterchen),verheiratetA(Person,Geschwisterchen).


% Schwaegerin ist Schwaegerin von Person :: schwaegerin(Schwaegerin,Person)
schwaegerin(Schwaegerin,Person):- weiblich(Schwaegerin),geschwister(Person,Geschwisterchen),verheiratetA(Schwaegerin,Geschwisterchen).
schwaegerin(Schwaegerin,Person):- weiblich(Schwaegerin),geschwister(Schwaegerin,Geschwisterchen),verheiratetA(Person,Geschwisterchen).
schwaegerin(Schwaegerin,Person):- weiblich(Schwaegerin),verheiratetA(Schwaegerin,Ehepartner),geschwister(Ehepartner,Geschwisterchen),verheiratetA(Person,Geschwisterchen).

