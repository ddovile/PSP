# Pastebejimai apie unit testus ir ka galima butu patobulinti:

- Nekurti `static final` klases instance, kadangi tai tiktai neleis keisti klasės adreso, bet klasės visi public metodai, kintamieji vis tiek bus pasiekiami.
  Pvz.: `private static final EmailValidator emailValidator = new EmailValidator();`
  
- Truksta unit testu, kad patikrintu prefiksa pagal skirtingas salis

- Email unit teste yra palikta klaida, oficialiai email local-domain dalis gali tureti "#" simboli

- Truksta tikrinimo, kas butu jeigu input butu tusti stringai

- Truksta tikrinimo, kas butu jeigu input butu null