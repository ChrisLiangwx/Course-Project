setwd("E:\\Projects\\Course Project\\APPL DATA ANALYTICS\\M2")
Auto = read.table("Auto.data", header=T, na.strings = c("?", " ", "NA"), stringsAsFactors = T)
Auto = na.omit(Auto)
attach(Auto)
head(Auto)

pairs(Auto)
cor(Auto[,1:8])


lm.fit = lm(mpg ~ .-name, data = Auto)
summary(lm.fit)

par(mfrow = c(2,2))
plot(lm.fit)


lm.fit1 = lm(mpg ~ . - name + year : origin + weight : displacement, data = Auto)
summary(lm.fit1)

lm.fit2 = lm(mpg ~ . - name - weight + I(weight^2), data = Auto)
summary(lm.fit2)

lm.fit3 = lm(mpg ~ . - name - horsepower + sqrt(horsepower), data = Auto)
summary(lm.fit3)

lm.fit4 = lm(mpg ~ . - name - origin + log(origin), data = Auto)
summary(lm.fit4)

