:-begin_tests(aufgabe1).
:-consult('ISaufgabe1').

test(schwager_1):- schwager(alex,alina).
test(schwager_2,[fail]):- schwager(sven,alina).
test(schwager_3,[fail]):- schwager(laura,alina).
test(schwager_4,[fail]):- schwager(sven,alex).
test(schwager_5,[fail]):- schwager(sven,sven).

test(schwaegerin_1):- schwaegerin(alina,alex).
test(schwaegerin_2,[fail]):- schwaegerin(alina,sven).
test(schwaegerin_3):- schwaegerin(laura,alina).
test(schwaegerin_4,[fail]):- schwaegerin(sven,alex).
test(schwaegerin_5,[fail]):- schwaegerin(laura,laura).

test(oma_1):- oma(ingeborg,anna).
test(oma_2):- oma(ingeborg,sabine).
test(oma_3,[fail]):- oma(ingeborg,alex).
test(oma_4,[fail]):- oma(ingeborg,sven).
test(oma_5,[fail]):- oma(ingeborg,peter).
test(oma_6,[fail]):- oma(ingeborg,ingeborg).


test(opa_1):- opa(peter,anna).
test(opa_2):- opa(peter,sabine).
test(opa_3,[fail]):- opa(peter,alex).
test(opa_4,[fail]):- opa(peter,sven).
test(opa_5,[fail]):- opa(peter,ingeborg).
test(opa_6,[fail]):- opa(peter,peter).

test(bruder_1):- bruder(alex,sven).
test(bruder_2,[fail]):- bruder(alex,alex).
test(bruder_3,[fail]):- bruder(brigitte,alex).
test(bruder_4,[fail]):- bruder(anna,peter).
test(bruder_5,[fail]):- bruder(alina,laura).

test(schwester_1):- schwester(sabine,berta).
test(schwester_2,[fail]):- schwester(alex,alex).
test(schwester_3,[fail]):- schwester(brigitte,alex).
test(schwester_4,[fail]):- schwester(anna,peter).
test(schwester_5,[fail]):- schwester(alina,laura).

test(onkel_1):- onkel(alex,sabine).
test(onkel_2,[fail]):- onkel(alex,anna).
test(onkel_3,[fail]):- onkel(alex,peter).
test(onkel_4,[fail]):- onkel(ingeborg,alex).
test(onkel_5,[fail]):- onkel(peter,sabine).
test(onkel_6,[fail]):- onkel(hans,alina).


test(tante_1):- tante(alina,anna).
test(tante_2,[fail]):- tante(alex,anna).
test(tante_3,[fail]):- tante(alex,peter).
test(tante_4,[fail]):- tante(ingeborg,alex).
test(tante_5,[fail]):- tante(peter,sabine).
test(tante_6,[fail]):- tante(hans,alina).

test(cousin_1,[fail]):- cousin(anna,sabine).
test(cousin_2,[fail]):- cousin(alex,anna).    
test(cousin_3,[fail]):- cousin(alex,peter).   
test(cousin_4,[fail]):- cousin(ingeborg,alex).
test(cousin_5,[fail]):- cousin(peter,sabine).

test(cousine_1):- cousine(anna,sabine).
test(cousine_2,[fail]):- cousine(alex,anna).    
test(cousine_3,[fail]):- cousine(alex,peter).   
test(cousine_4,[fail]):- cousine(ingeborg,alex).
test(cousine_5,[fail]):- cousine(peter,sabine).

test(vorfahre_1):- vorfahre(peter,alex).
test(vorfahre_2):- vorfahre(peter,anna).
test(vorfahre_3):- vorfahre(alex,anna).
test(vorfahre_4,[fail]):- vorfahre(anna,alex).
test(vorfahre_5,[fail]):- vorfahre(anna,peter).
test(vorfahre_6,[fail]):- vorfahre(alex,peter).
test(vorfahre_7,[fail]):- vorfahre(alex,alex).
test(vorfahre_8,[fail]):- vorfahre(alex,sven).
test(vorfahre_9,[fail]):- vorfahre(alex,laura).


:-end_tests(aufgabe1).