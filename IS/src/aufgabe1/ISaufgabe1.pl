% Autor:
% Datum: 18.03.2014

%
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

vater(peter,hans).
vater(peter,alex).
vater(peter,sven).

vater(hans,christiane).
vater(hans,dorothea).

vater(alex,brigitte).
vater(alex,anna).

vater(sven,sabine).
vater(sven,berta).

mutter(ingeborg,hans).
mutter(ingeborg,alex).
mutter(ingeborg,sven).

mutter(charline,christiane).
mutter(charline,dorothea).

mutter(laura,brigitte).
mutter(laura,anna).

mutter(alina,sabine).
mutter(alina,berta).

oma(Oma,Kind):- vater(X,Kind),mutter(Oma,X).
oma(Oma,Kind):- mutter(X,Kind),mutter(Oma,X).

opa(Opa,Kind):- vater(X,Kind),vater(Opa,X).
opa(Opa,Kind):- mutter(X,Kind),vater(Opa,X).

%vorfahre(Vorfahre, Person):-  listeniteration!?





