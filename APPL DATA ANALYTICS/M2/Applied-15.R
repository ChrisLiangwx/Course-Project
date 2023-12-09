library(MASS)
library(ISLR2)

head(Boston)
attach(Boston)

#simple linear
lm.zn = lm(crim~zn, data=Boston)
lm.indus = lm(crim~indus, data=Boston)
lm.chas = lm(crim~chas, data=Boston)
lm.nox = lm(crim~nox, data=Boston)
lm.rm = lm(crim~rm, data=Boston)
lm.age = lm(crim~age, data=Boston)
lm.dis = lm(crim~dis, data=Boston)
lm.rad = lm(crim~rad, data=Boston)
lm.tax = lm(crim~tax, data=Boston)
lm.ptratio = lm(crim~ptratio, data=Boston)
lm.black = lm(crim~black, data=Boston)
lm.lstat = lm(crim~lstat, data=Boston)
lm.medv = lm(crim~medv, data=Boston)
lm(crim~zn, data=Boston)
lm(crim~indus, data=Boston)
lm(crim~chas, data=Boston)
lm(crim~nox, data=Boston)
lm(crim~rm, data=Boston)
lm(crim~age, data=Boston)
lm(crim~dis, data=Boston)
lm(crim~rad, data=Boston)
lm(crim~tax, data=Boston)
lm(crim~ptratio, data=Boston)
lm(crim~black, data=Boston)
lm(crim~lstat, data=Boston)
lm(crim~medv, data=Boston)
plot(rm,crim)
abline(lm.rm, lwd =5, col ="red")


#multiple linear

lm.fit = lm(crim ~ ., data = Boston)
summary(lm.fit)

#non-linear
nlm_zn = lm(crim ~ zn + I(zn^2) + I(zn^3), data = Boston)
summary(nlm_zn)

nlm_indus = lm(crim ~ indus + I(indus^2) + I(indus^3), data = Boston)
summary(nlm_indus)

nlm_chas = lm(crim ~ chas + I(chas^2) + I(chas^3), data = Boston)
summary(nlm_chas)

nlm_nox = lm(crim ~ nox + I(nox^2) + I(nox^3), data = Boston)
summary(nlm_nox)

nlm_rm = lm(crim ~ rm + I(rm^2) + I(rm^3), data = Boston)
summary(nlm_rm)

nlm_age = lm(crim ~ age + I(age^2) + I(age^3), data = Boston)
summary(nlm_age)

nlm_dis = lm(crim ~ dis + I(dis^2) + I(dis^3), data = Boston)
summary(nlm_dis)

nlm_rad = lm(crim ~ rad + I(rad^2) + I(rad^3), data = Boston)
summary(nlm_rad)

nlm_tax = lm(crim ~ tax + I(tax^2) + I(tax^3), data = Boston)
summary(nlm_tax)

nlm_ptratio = lm(crim ~ ptratio + I(ptratio^2) + I(ptratio^3), data = Boston)
summary(nlm_ptratio)

nlm_lstat = lm(crim ~ lstat + I(lstat^2) + I(lstat^3), data = Boston)
summary(nlm_lstat)

nlm_medv = lm(crim ~ medv + I(medv^2) + I(medv^3), data = Boston)
summary(nlm_medv)


