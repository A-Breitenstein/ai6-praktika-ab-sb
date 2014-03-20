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



% X ist Oma von Y :: oma(X,Y)
oma(Oma,Kind):- vater(X,Kind),mutter(Oma,X).
oma(Oma,Kind):- mutter(X,Kind),mutter(Oma,X).

opa(Opa,Kind):- vater(X,Kind),vater(Opa,X).
opa(Opa,Kind):- mutter(X,Kind),vater(Opa,X).

% X ist vorfahre von Y  ::  vorfahre(X,Y)
vorfahre(Vorfahre, Vorfahre).
vorfahre(Vorfahre, Person):- mutter(NeuePerson,Person), vorfahre(Vorfahre, NeuePerson).
vorfahre(Vorfahre, Person):- vater(NeuePerson,Person), vorfahre(Vorfahre, NeuePerson).

% X ist Bruder von Y :: bruder(X,Y)
bruder(Bruder,Person):- vater(Vater,Bruder),vater(Vater,Person),maennlich(Bruder).
bruder(Bruder,Person):- mutter(Mutter,Bruder),mutter(Mutter,Person),maennlich(Bruder).

% X ist Schwester von Y :: schwester(X,Y)
schwester(Schwester,Person):- vater(Vater,Schwester),vater(Vater,Person),weiblich(Schwester).
schwester(Schwester,Person):- mutter(Mutter,Schwester),mutter(Mutter,Person),weiblich(Schwester).


% X ist Onkel von Y :: onkel(X,Y)
onkel(Onkel,Person):- vater(Vater,Person),bruder(Onkel,Vater).
onkel(Onkel,Person):- mutter(Mutter,Person),bruder(Onkel,Mutter).

% X ist Tante von Y :: tante(X,Y)
tante(Tante,Person):- vater(Vater,Person),bruder(Tante,Vater).
tante(Tante,Person):- mutter(Mutter,Person),bruder(Tante,Mutter).

% X ist Cousin von Y :: cousin(X,Y)
cousin(Cousin,Person):- (vater(X,Cousin);mutter(X,Cousin)),
                        (vater(Y,Person);mutter(Y,Person)),
                        (bruder(X,Y);schwester(X,Y);bruder(Y,X);schwester(Y,X)),
                        maennlich(Cousin).
                        
                        
% X ist Cousine von Y :: cousine(X,Y)
cousine(Cousine,Person):- (vater(X,Cousine);mutter(X,Cousine)),
                        (vater(Y,Person);mutter(Y,Person)),
                        (bruder(X,Y);schwester(X,Y);bruder(Y,X);schwester(Y,X)),
                        weiblich(Cousine).

% X ist Schwager von Y :: schwager(X,Y)
schwager(X,X):- !,fail.
schwager(X,Y):- ehemann(X,Y),!,fail.
schwager(X,Y):- schwester(G,Y), ehemann(X,G).
schwager(X,Y):- ehemann(Yn,Y),bruder(X,Yn).
schwager(X,Y):- ehemann(Yn,Y),schwester(G,Yn), ehemann(X,G).


% X ist Schwaegerin von Y :: schwaegerin(X,Y)
schwaegerin(X,X):- !,fail.
schwaegerin(X,Y):- ehemann(Y,X),!,fail.
schwaegerin(X,Y):- bruder(G,Y), ehemann(G,X).
schwaegerin(X,Y):- ehemann(Yn,Y),schwester(G,Yn).
schwaegerin(X,Y):- ehemann(Yn,Y),bruder(G,Yn),ehemann(G,X).
