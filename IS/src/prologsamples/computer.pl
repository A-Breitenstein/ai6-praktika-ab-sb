type(amd600,proc).        type(amd1500,proc).
type(cd_sony,cd).         type(cd_benq,cd).
type(ati32,graphic).      type(ati64,graphic).

prop(amd600,speed,slow).  prop(amd1500,speed,fast).
prop(cd_sony,speed,slow). prop(cd_benq,speed,fast).
prop(ati32,speed,slow).   prop(ati64,speed,fast).

prop(amd600,price,100).   prop(amd1500,price,250).
prop(cd_sony,price,60).   prop(cd_benq,price,100).
prop(ati32,price,100).    prop(ati64,price,200).

processor(Proc,Speed,Price) :-
  type(Proc,proc),
  prop(Proc,speed,Speed),
  prop(Proc,price,Price).

cd(CD,Speed,Price) :-
  type(CD,cd),
  prop(CD,speed,Speed),
  prop(CD,price,Price).

graphic(Graphic,Speed,Price) :-
  type(Graphic,graphic),
  prop(Graphic,speed,Speed),
  prop(Graphic,price,Price).

speed(fast,fast,fast,fast) :- !.
speed(_,_,_,slow).

max_price(P1,P2,P3,Sum,Max) :-
  nonvar(Max), !, sum(P1,P2,P3,Sum), !, Sum =< Max.
max_price(P1,P2,P3,Sum,Sum) :-
  !, sum(P1,P2,P3,Sum).

sum(X,Y,Z,Sum) :- (var(X);var(Y)), plus(Temp,Z,Sum), plus(X,Y,Temp).
sum(X,Y,Z,Sum) :- (var(Z);var(Sum)), plus(X,Y,Temp), plus(Temp,Z,Sum).

computer_system([Proc,CD,Graphic], Speed, Price, MaxPrice) :-
  processor(Proc,ProcS,ProcP),
  cd(CD,CDS,CDP),
  graphic(Graphic,GraphicS,GraphicP),
  speed(ProcS,CDS,GraphicS,Speed),
  max_price(ProcP,CDP,GraphicP,Price, MaxPrice).
