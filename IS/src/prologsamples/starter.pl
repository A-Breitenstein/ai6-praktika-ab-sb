% Rule 1

noStarter :- 
  noPowerStarter; frozenEngine; brokenStarter.

% Rule 2

noPowerStarter :- 
  noPowerAct; brokenCableSt; brokenCableSol.

% Rule 3

noPowerAct :- 
  noPowerStSw; offStSw; brokenStSw.

% Rule 4

noPowerStSw :- 
  dischargedBatt; brokenCableStSw.

% Rule 5

noPowerLiSw :- 
 dischargedBatt; brokenCableLiSw.

% Rule 6

noHeadLight :- 
  noPowerLiSw; burnedOutBulb; offHeadlight.

