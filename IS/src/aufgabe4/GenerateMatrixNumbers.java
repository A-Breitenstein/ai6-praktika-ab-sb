package aufgabe4;

/**
 * Created by abg667 on 22.05.14.
 */
public class GenerateMatrixNumbers {
    public static void main(String[] args) {
        int matrixSize = 9;
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                System.out.print("M"+j+i+",");
            }
            System.out.println();
        }
    }
}
//  M00 = 6,M01,M02,          M03 = 7,M04,M05,        M06 = 5,M07,M08,
//  M10,M11 = 2,M12 = 8,      M13,M14,M15,            M16,M17,M18,
//  M20,M21,M22,              M23 = 6,M24 = 4,M25,    M26 = 3,M27,M28,
//
//  M30 = 7,M31 = 4,M32,      M33,M34,M35,            M36,M37 = 2,M38,
//  M40,M41,M42 = 1,          M43,M44,M45,            M46 = 8,M47,M48,
//  M50,M51 = 5,M52,          M53,M54,M55,            M56,M57 = 3,M58 = 7,
//
//  M60,M61,M62 = 3,          M63,M64 = 7,M65 = 6,    M66,M67,M68,
//  M70,M71,M72,              M73,M74,M75,            M76 = 1,M77 = 9,M78,
//  M80,M81,M82 = 4,          M83,M84,M85 = 5,        M86,M87,M88 = 8,
//
//
//
//Q1 =  [M00 ,M01,M02,
//        M10,M11,M12,
//        M20,M21,M22 ],
//
//Q2 = [M03 ,M04,M05,
//        M13,M14,M15,
//        M23 ,M24,M25],
//
//Q3= [M06 ,M07,M08,
//        M16,M17,M18,
//        M26 ,M27,M28],
//
//Q4 = [M30,M31,M32,
//        M40,M41,M42,
//        M50,M51,M52 ],
//
//Q5 =  [M33,M34,M35,
//        M43,M44,M45,
//        M53,M54,M55],
//
//Q6 = [M36,M37 ,M38,
//        M46 ,M47,M48,
//        M56,M57 ,M58 ],
//
//Q7 = [M60,M61,M62,
//        M70,M71,M72,
//        M80,M81,M82],
//
//Q8= [ M63,M64 ,M65,
//        M73,M74,M75,
//        M83,M84,M85],
//
//Q9 =  [M66,M67,M68,
//        M76 ,M77 ,M78,
//        M86,M87,M88 ],
//
//
//    Zeile1 = [M00,M01,M02,M03,M04,M05,M06,M07,M08],
//    Zeile2 = [M10,M11,M12,M13,M14,M15,M16,M17,M18],
//    Zeile3 = [M20,M21,M22,M23,M24,M25,M26,M27,M28],
//    Zeile4 = [M30,M31,M32,M33,M34,M35,M36,M37,M38],
//    Zeile5 = [M40,M41,M42,M43,M44,M45,M46,M47,M48],
//    Zeile6 = [M50,M51,M52,M53,M54,M55,M56,M57,M58],
//    Zeile7 = [M60,M61,M62,M63,M64,M65,M66,M67,M68],
//    Zeile8 = [M70,M71,M72,M73,M74,M75,M76,M77,M78],
//    Zeile9 = [M80,M81,M82,M83,M84,M85,M86,M87,M88],
//    Spalte1 =[M00,M10,M20,M30,M40,M50,M60,M70,M80],
//    Spalte2 =[M01,M11,M21,M31,M41,M51,M61,M71,M81],
//    Spalte3 =[M02,M12,M22,M32,M42,M52,M62,M72,M82],
//    Spalte4 =[M03,M13,M23,M33,M43,M53,M63,M73,M83],
//    Spalte5 =[M04,M14,M24,M34,M44,M54,M64,M74,M84],
//    Spalte6 =[M05,M15,M25,M35,M45,M55,M65,M75,M85],
//    Spalte7 =[M06,M16,M26,M36,M46,M56,M66,M76,M86],
//    Spalte8 =[M07,M17,M27,M37,M47,M57,M67,M77,M87],
//    Spalte9 =[M08,M18,M28,M38,M48,M58,M68,M78,M88],
//                all_different(Q1),
//                all_different(Q2),
//                all_different(Q3),
//                all_different(Q4),
//                all_different(Q5),
//                all_different(Q6),
//                all_different(Q7),
//                all_different(Q8),
//                all_different(Q9),
//                all_different(Zeile1),
//                all_different(Zeile2),
//                all_different(Zeile3),
//                all_different(Zeile4),
//                all_different(Zeile5),
//                all_different(Zeile6),
//                all_different(Zeile7),
//                all_different(Zeile8),
//                all_different(Zeile9),
//                all_different(Spalte1),
//                all_different(Spalte2),
//                all_different(Spalte3),
//                all_different(Spalte4),
//                all_different(Spalte5),
//                all_different(Spalte6),
//                all_different(Spalte7),
//                all_different(Spalte8),
//                all_different(Spalte9),