# Programų Sistemų Projektavimas. Laboratoriniai darbai
Dovilė Dikovičiūtė 3 grupė
# 1.2. užduotis
Validatoriai realizuoti pagal [Tomo testus](https://github.com/Tomas-Per/ValidationModule/tree/master)
1) Ar buvo aiškus ir patogus unit testai, ar kodas aiškus.
   Taip. Problemų kilo tik su telefono numerio testais, tačiau net ir jie buvo aiškūs.
2) Kaip jus galėtumete juos pagerinti?
   Kaip ir paminėjau [PR](https://github.com/ddovile/PSP/pull/4): galėtų truputėli daugiau testų tikrinti elektroninio pašto domeną. Naujos telefono numerio validavimo taisyklės galėtų būti tiesiog pridedamos nesitikint, kad validatorius jau žinos tas taisykles ir validuos jas (validuoti validacijos taisykles neskamba teisingai). Taip pat, validavimo taisyklės bus ne tik pridedamos, bet ir tikrinami numeriai pagal jas.
3) Kokius unit testus jus galėtumėte pridėti (jei tokių yra)? Tikrinamas paštas, kai domene yra neleistinų simbolių. Tikrinamas numeris pagal naujai sukurtas validacijos taisykles.
