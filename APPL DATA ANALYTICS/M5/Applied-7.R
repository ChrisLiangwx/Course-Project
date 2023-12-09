library(ISLR2)
library(e1071)
library(caTools)

#load Auto
setwd("E:\\Projects\\Course Project\\APPL DATA ANALYTICS\\M5")
Auto = read.table("Auto.data", header=T, na.strings = c("?", " ", "NA"), stringsAsFactors = T)
Auto = na.omit(Auto)
attach(Auto)

set.seed(1)
head(Auto)
mpg.median = median(mpg)
Auto$mpg_binary = ifelse(Auto$mpg > mpg.median, 1, 0)
head(Auto)

#SVM linear
tune.linear = tune(svm, mpg_binary~.-mpg, data = Auto, kernel = 'linear', 
                ranges = list(cost = c(0.001, 0.01, 0.1, 1, 5, 10, 100)))
summary(tune.linear)
bestmod = tune.linear$best.model
plot(bestmod, Auto, horsepower ~ weight)


#plot
svm_model_2d = svm(mpg_binary ~ weight + horsepower, data = Auto, kernel = "linear", cost = 1)

grid_range = expand.grid(weight = seq(min(Auto$weight), max(Auto$weight), length.out = 100),
                          horsepower = seq(min(Auto$horsepower), max(Auto$horsepower), length.out = 100))

grid_range$mpg_binary = predict(svm_model_2d, newdata = grid_range)

plot(Auto$weight, Auto$horsepower, col = Auto$mpg_binary + 1, xlab = "Weight", ylab = "Horsepower", pch = 19)






#SVM non-linear
tune.nonlinear = tune(svm, mpg_binary~.-mpg, data = Auto, kernel = 'radial', 
                   ranges = list(cost = c(0.001, 0.01, 0.1, 1, 5, 10, 100), gamma = c(0.5, 1, 1.5, 2, 2.5, 3)))
bestmod = tune.linear$best.model
summary(tune.nonlinear)
bestmod
plot(bestmod, Auto, horsepower ~ weight)


tune.nonlinear = tune(svm, mpg_binary~.-mpg, data = Auto, kernel = 'polynomial', 
                      ranges = list(cost = c(0.001, 0.01, 0.1, 1, 5, 10, 100), degree = 1:5))
bestmod = tune.linear$best.model
summary(tune.nonlinear)
bestmod$degree
plot(bestmod, Auto, horsepower ~ weight)
