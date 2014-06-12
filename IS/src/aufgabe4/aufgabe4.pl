% Autor:
% Datum: 22.05.2014
:- use_module(library(clpfd)).

%%sodukuh([2,3],[1,9],[[0,0,6],[0,3,7]]):-
makeMatrix(0,_,Tmp,Out):- Out = Tmp.
makeMatrix(X,Y,Tmp,Out):-

                     length(Spalte,Y),
                     append(Tmp,[Spalte],TmpNeu),
                     Xneu is X-1,
                     makeMatrix(Xneu,Y,TmpNeu,Out).
belegungMachen(Matrix,[]).
belegungMachen(Matrix,[[X,Y,Elem]|Rest]):-
                                          nth0(X,Matrix,Spalte),
                                          nth0(Y,Spalte,Elem),
                                          belegungMachen(Matrix,Rest).
holeZeile(_,-1,_,Tmp,Out):- Out = Tmp.
holeZeile(Matrix,X,Y,Tmp,Out):-
                             nth0(X,Matrix,Spalte),
                             nth0(Y,Spalte,Feld),
                             Xneu is X -1,
                             holeZeile(Matrix,Xneu,Y,[Feld|Tmp],Out).

holeAlleZeilen(Matrix,X,0,Tmp,Out):- Out = Tmp.
holeAlleZeilen(Matrix,X,Y,Tmp,Out):-
                                    Yneu is Y-1,
                                    holeZeile(Matrix,X,Yneu,[],Zeile),
                                    holeAlleZeilen(Matrix,X,Yneu,[Zeile|Tmp],Out).

holeQuadrant(_,_,_,0,_,_,Tmp,[Out|Tmp]).
holeQuadrant(AlleZeilen,IndexX,IndexY,Xq,Yq,Berechnung,Tmp,Out):-
                                                                 IndexZeile is IndexX * Berechnung,
                                                                 IndexZeileTeil is IndexY * Yq,
                                                                 nth0(IndexZeile,AlleZeilen,AktuelleZeile),
                                                                 holeSubList(IndexZeileTeil,Yq,AktuelleZeile,[],Sublist),
                                                                 XqNeu is Xq - 1,
                                                                 BerechnungNeu is Berechnung +1,
                                                                 holeQuadrant(AlleZeilen,IndexX,IndexY,XqNeu,Yq,BerechnungNeu,[Sublist|Tmp],Out).

holeSublist(Start,0,Liste,Tmp,Out) :- Out = Tmp.
holeSublist(Start,Laenge,Liste,Tmp,Out) :-
                                        StartNeu is Start + 1,
                                        LaengeNeu is Laenge - 1,
                                        nth0(Start,Liste,Elem),
                                        holeSublist(StartNeu,LaengeNeu,Liste,[Elem|Tmp],Out).

holeAlleQuadranten(AlleZeilen,Xq,Yq,Tmp,Out)
holeAlleQuadranten(AlleZeilen,Xq,Yq,Tmp,Out):-


sodukuh([   M00,M01,M02,      M03,M04,M05,      M06,M07,M08,
            M10,M11,M12,      M13,M14,M15,      M16,M17,M18,
            M20,M21,M22,      M23,M24,M25,      M26,M27,M28,

            M30,M31,M32,      M33,M34,M35,      M36,M37,M38,
            M40,M41,M42,      M43,M44,M45,      M46,M47,M48,
            M50,M51,M52,      M53,M54,M55,      M56,M57,M58,

            M60,M61,M62,      M63,M64,M65,      M66,M67,M68,
            M70,M71,M72,      M73,M74,M75,      M76,M77,M78,
            M80,M81,M82,      M83,M84,M85,      M86,M87,M88]) :-

M00 = 6,M03 = 7,M06 = 5,M11 = 2,M12 = 8,M23 = 6,M24 = 4,M26 = 3,
M30 = 7,M31 = 4,M37 = 2,M42 = 1,M46 = 8,M51 = 5,M57 = 3,M58 = 7,
M62 = 3,M64 = 7,M65 = 6,M76 = 1,M77 = 9,M82 = 4,M85 = 5,M88 = 8,

Q1 =  [M00 ,M01,M02,
        M10,M11,M12,
        M20,M21,M22 ],

Q2 = [M03 ,M04,M05,
        M13,M14,M15,
        M23 ,M24,M25],

Q3= [M06 ,M07,M08,
        M16,M17,M18,
        M26 ,M27,M28],

Q4 = [M30,M31,M32,
        M40,M41,M42,
        M50,M51,M52 ],

Q5 =  [M33,M34,M35,
        M43,M44,M45,
        M53,M54,M55],

Q6 = [M36,M37 ,M38,
        M46 ,M47,M48,
        M56,M57 ,M58 ],

Q7 = [M60,M61,M62,
        M70,M71,M72,
        M80,M81,M82],

Q8= [ M63,M64 ,M65,
        M73,M74,M75,
        M83,M84,M85],

Q9 =  [M66,M67,M68,
        M76 ,M77 ,M78,
        M86,M87,M88 ],


   Zeile1 = [M00,M01,M02,M03,M04,M05,M06,M07,M08],
   Zeile2 = [M10,M11,M12,M13,M14,M15,M16,M17,M18],
   Zeile3 = [M20,M21,M22,M23,M24,M25,M26,M27,M28],
   Zeile4 = [M30,M31,M32,M33,M34,M35,M36,M37,M38],
   Zeile5 = [M40,M41,M42,M43,M44,M45,M46,M47,M48],
   Zeile6 = [M50,M51,M52,M53,M54,M55,M56,M57,M58],
   Zeile7 = [M60,M61,M62,M63,M64,M65,M66,M67,M68],
   Zeile8 = [M70,M71,M72,M73,M74,M75,M76,M77,M78],
   Zeile9 = [M80,M81,M82,M83,M84,M85,M86,M87,M88],

   Spalte1 =[M00,M10,M20,M30,M40,M50,M60,M70,M80],
   Spalte2 =[M01,M11,M21,M31,M41,M51,M61,M71,M81],
   Spalte3 =[M02,M12,M22,M32,M42,M52,M62,M72,M82],
   Spalte4 =[M03,M13,M23,M33,M43,M53,M63,M73,M83],
   Spalte5 =[M04,M14,M24,M34,M44,M54,M64,M74,M84],
   Spalte6 =[M05,M15,M25,M35,M45,M55,M65,M75,M85],
   Spalte7 =[M06,M16,M26,M36,M46,M56,M66,M76,M86],
   Spalte8 =[M07,M17,M27,M37,M47,M57,M67,M77,M87],
   Spalte9 =[M08,M18,M28,M38,M48,M58,M68,M78,M88],

  Q1 ins 1..9,
  Q2 ins 1..9,
  Q3 ins 1..9,
  Q4 ins 1..9,
  Q5 ins 1..9,
  Q6 ins 1..9,
  Q7 ins 1..9,
  Q8 ins 1..9,
  Q9 ins 1..9,
  Zeile1 ins 1..9,
  Zeile2 ins 1..9,
  Zeile3 ins 1..9,
  Zeile4 ins 1..9,
  Zeile5 ins 1..9,
  Zeile6 ins 1..9,
  Zeile7 ins 1..9,
  Zeile8 ins 1..9,
  Zeile9 ins 1..9,
  Spalte1 ins 1..9,
  Spalte2 ins 1..9,
  Spalte3 ins 1..9,
  Spalte4 ins 1..9,
  Spalte5 ins 1..9,
  Spalte6 ins 1..9,
  Spalte7 ins 1..9,
  Spalte8 ins 1..9,
  Spalte9 ins 1..9,
  
               all_different(Q1),
               all_different(Q2),
               all_different(Q3),
               all_different(Q4),
               all_different(Q5),
               all_different(Q6),
               all_different(Q7),
               all_different(Q8),
               all_different(Q9),
               all_different(Zeile1),
               all_different(Zeile2),
               all_different(Zeile3),
               all_different(Zeile4),
               all_different(Zeile5),
               all_different(Zeile6),
               all_different(Zeile7),
               all_different(Zeile8),
               all_different(Zeile9),
               all_different(Spalte1),
               all_different(Spalte2),
               all_different(Spalte3),
               all_different(Spalte4),
               all_different(Spalte5),
               all_different(Spalte6),
               all_different(Spalte7),
               all_different(Spalte8),
               all_different(Spalte9),
               label(Q1),
               label(Q2),
               label(Q3),
               label(Q4),
               label(Q5),
               label(Q6),
               label(Q7),
               label(Q8),
               label(Q9),
               label(Zeile1),
               label(Zeile2),
               label(Zeile3),
               label(Zeile4),
               label(Zeile5),
               label(Zeile6),
               label(Zeile7),
               label(Zeile8),
               label(Zeile9),
               label(Spalte1),
               label(Spalte2),
               label(Spalte3),
               label(Spalte4),
               label(Spalte5),
               label(Spalte6),
               label(Spalte7),
               label(Spalte8),
               label(Spalte9),

              writeresults([Zeile1,Zeile2,Zeile3,Zeile4,Zeile5,Zeile6,Zeile7,Zeile8,Zeile9]).




writeresults([]).
writeresults([A|T]):-writeln(A), writeresults(T).
                