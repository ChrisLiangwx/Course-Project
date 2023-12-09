library(ISLR2)
library(e1071)
library(caTools)

set.seed(1)
x1 = runif(500) - 0.5
x2 = runif(500) - 0.5
y = 1*(x1^2-x2^2>0)
data = data.frame(x1 = x1, x2 = x2, y = as.factor(y))
plot(x1, x2, col = y + 1)

#logistic regression
lr.fit = glm(y ~ x1 + x2, data = data, family = 'binomial')
lr.probs = predict(lr.fit, newdata = data, type = 'response')
lr.preds = rep(0,500)
lr.preds[lr.probs >0.5] = 1
table(lr.preds, data$y)
plot(x1, x2, col = lr.preds + 1)

#lr non-linear
lr.fit2 = glm(y~I(x1^2) + I(x2^2), data = data, family = 'binomial')
lr.probs2 = predict(lr.fit2, newdata = data, type = 'response')
lr.preds2 = rep(0,500)
lr.preds2[lr.probs2 >0.5] = 1
table(lr.preds2, data$y)
plot(x1, x2, col = lr.preds2 + 1)


#SVM linear kernel
set.seed(1)
tune.out = tune(svm, y~x1+x2, data = data, kernel = 'linear', 
                ranges = list(cost = c(0.001, 0.01, 0.1, 1, 5, 10, 100)))
bestmod = tune.out$best.model
plot(bestmod, data)
#SVM non-linear kernel
set.seed(1)
tune.out = tune(svm, y~x1+x2, data = data, kernel = 'polynomial', 
                ranges = list(degree = 1:2, cost = c(0.001, 0.01, 0.1, 1, 5, 10, 100)))
bestmod = tune.out$best.model
plot(bestmod, data)
