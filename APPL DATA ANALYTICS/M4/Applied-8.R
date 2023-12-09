library(tree)
library(ISLR2)
library(randomForest)
library(caTools)
library(BART)

head(Carseats)
attach(Carseats)
set.seed(6)

#fit
sample.data = sample.split(Carseats$Sales, SplitRatio = 0.7)
train.set = subset(Carseats, sample.data)
test.set = subset(Carseats, !sample.data)
tree.fit = tree(Sales ~ ., data = train.set)
summary(tree.fit)
plot(tree.fit)
text(tree.fit, pretty = 0)

#calculate mse
tree.preds = predict(tree.fit, test.set)
tree.mse = mean((tree.preds - test.set$Sales)^2)
tree.mse

#cross-validation
cv.tree.fit = cv.tree(tree.fit)
plot(cv.tree.fit$size, cv.tree.fit$dev, type = "b")
cv.tree.fit

#prune
opt.size = which.min(cv.tree.fit$dev)
pruned.tree = prune.tree(tree.fit, best = cv.tree.fit$size[opt.size])
plot(pruned.tree)
text(pruned.tree, pretty = 0)

test.pred = predict(pruned.tree, test.set)
test.mse = mean((test.set$Sales - test.pred)^2)
test.mse

#bagging
bagging.fit = randomForest(Sales ~ ., data = train.set, mtry = (ncol(train.set) - 1)/3, importance = T)
bagging.pred = predict(bagging.fit, test.set)
bagging.mse = mean((test.set$Sales - bagging.pred)^2)
bagging.mse
importance(bagging.fit)

#random forest
rf.fit = randomForest(Sales ~ ., data = train.set, importance = T)
rf.pred = predict(rf.fit, test.set)
rf.mse = mean((test.set$Sales - rf.pred)^2)
rf.mse

importance(rf.fit)

#BART
xtrain = train.set[, -which(names(train.set) == "Sales")]
ytrain = train.set$Sales
xtest = test.set[, -which(names(test.set) == "Sales")]
ytest = test.set$Sales
bart.fit = gbart(xtrain, ytrain, x.test = xtest)
bart.pred = bart.fit$yhat.test.mean
bart.mse = mean((ytest - bart.pred)^2)
bart.mse
