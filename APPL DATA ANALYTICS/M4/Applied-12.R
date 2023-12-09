library(tree)
library(ISLR2)
library(randomForest)
library(caTools)
library(BART)
library(gbm)

data(package = 'ISLR2')
head(Bikeshare)
attach(Bikeshare)
set.seed(1)

#tree
sample.data = sample.split(Bikeshare$bikers, SplitRatio = 0.6)
train.set = subset(Bikeshare, sample.data)
test.set = subset(Bikeshare, !sample.data)
tree.fit = tree(bikers ~ ., data = train.set)
tree.pred = predict(tree.fit, newdata = test.set)
tree.mse = mean((test.set$bikers - tree.pred)^2)
tree.mse

#boosting
shrinkage_values = seq(0.01, 0.1, by = 0.01)
test.mse = numeric(length(shrinkage_values))

for (i in seq_along(shrinkage_values)) {
  set.seed(1)
  boost.fit = gbm(bikers ~ ., data = train.set, distribution = "gaussian",
                  n.trees = 1000, interaction.depth = 1, 
                  shrinkage = shrinkage_values[i])
  
  train.pred = predict(boost.fit, train.set, n.trees = 1000)
  test.pred = predict(boost.fit, test.set, n.trees = 1000)
  test.mse[i] = mean((test.set$bikers - test.pred)^2)
}
test.mse


#bagging
bagging.fit = randomForest(bikers ~ ., data = train.set, mtry = 5)
bagging.pred = predict(bagging.fit, test.set)
bagging.mse = mean((test.set$bikers - bagging.pred)^2)
bagging.mse


#random forest
rf.fit = randomForest(bikers ~ ., data = train.set)
rf.pred = predict(rf.fit, test.set)
rf.mse = mean((test.set$bikers - rf.pred)^2)
rf.mse


#BART
xtrain = train.set[, -which(names(train.set) == "bikers")]
ytrain = train.set$bikers
xtest = test.set[, -which(names(test.set) == "bikers")]
ytest = test.set$bikers
bart.fit = gbart(xtrain, ytrain, x.test = xtest)
bart.pred = bart.fit$yhat.test.mean
bart.mse = mean((ytest - bart.pred)^2)
bart.mse


#Linear
lm.fit = lm(bikers~., data = train.set)
lm.pred = predict(lm.fit, newdata = test.set)
lm.mse = mean((test.set$bikers - lm.pred)^2)
lm.mse