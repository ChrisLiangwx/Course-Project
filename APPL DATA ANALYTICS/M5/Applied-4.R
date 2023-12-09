library(ISLR2)
library(e1071)
library(caTools)
set.seed(1)
x = matrix(rnorm(100 * 2), ncol = 2)
y = c(rep(-1, 60), rep(1, 40))
x[1:30, ] = x[1:30, ] + 3
x[31:60, ] = x[31:60, ] -3
data = data.frame(x=x, y=as.factor(y))
plot(x, col = y + 2)

sample.data = sample.split(data$x.1, SplitRatio = 0.6)
train.set = subset(data, sample.data)
test.set = subset(data, !sample.data)

#polynomial kernel
set.seed(1)
tune.out = tune(svm, y~., data = train.set, kernel = 'polynomial', 
                ranges = list(degree = 1:5, cost = c(0.001, 0.01, 0.1, 1, 5, 10, 100)))
bestmod = tune.out$best.model
plot(bestmod, data)

ypred = predict(bestmod, test.set)
table(predict = ypred, truth = test.set$y)

#radial kernel
set.seed(1)
tune.out = tune(svm, y~., data = train.set, kernel = 'radial', 
                ranges = list(cost = c(0.001, 0.01, 0.1, 1, 5, 10, 100)))
bestmod = tune.out$best.model
plot(bestmod, data)

ypred = predict(bestmod, test.set)
table(predict = ypred, truth = test.set$y)
