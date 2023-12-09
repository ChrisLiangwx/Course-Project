
library(ISLR2)
library(MASS)
carseats_lm = lm(Sales ~ Price + Urban + US, data = Carseats)
summary(carseats_lm)

carseats_lm1 = lm(Sales ~ ., data = Carseats)
summary(carseats_lm1)


carseats_lm2 = lm(Sales ~ CompPrice + Income + Advertising + Price + ShelveLoc + Age, data = Carseats)
summary(carseats_lm2)
confint(carseats_lm2)

par(mfrow = c(2,2))
plot(carseats_lm2)
