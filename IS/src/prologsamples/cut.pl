f_without_cut([A, B, C]) :- a(A), b(B), c(C).

f_with_cut([A, B, C]) :- a(A), !, b(B), c(C).

a(a1). a(a2). b(b1). b(b2). c(c1). c(c2).
