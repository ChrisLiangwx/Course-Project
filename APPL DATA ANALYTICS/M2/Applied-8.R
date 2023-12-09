setwd("E:\\Projects\\Course Project\\APPL DATA ANALYTICS\\M2")
Auto = read.table("Auto.data", header=T, na.strings = c("?", " ", "NA"), stringsAsFactors = T)
Auto = na.omit(Auto)
attach(Auto)
lm.fit = lm(mpg ~ horsepower, data = Auto)
summary(lm.fit)

predict(lm.fit, data.frame(horsepower = 98 ), interval = "confidence")
predict(lm.fit, data.frame(horsepower = 98 ) , interval = "prediction")

plot(mpg ~ horsepower, data = Auto)
abline(lm(mpg ~ horsepower, data = Auto), col = "red")

par(mfrow = c(2, 2))
plot(lm.fit)
