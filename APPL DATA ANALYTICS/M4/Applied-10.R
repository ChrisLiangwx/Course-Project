library(tree)
library(ISLR2)
library(randomForest)
library(caTools)
library(BART)
library(gbm)
attach(Hitters)
head(Hitters)

Hitters = na.omit(Hitters)
Hitters$Salary = log(Hitters$Salary)

#prepare test and train set
set.seed(1)
sample.data = sample.split(Hitters$Salary, SplitRatio = 200/nrow(Hitters))
train.set = subset(Hitters, sample.data)
test.set = subset(Hitters, !sample.data)


#boosting
shrinkage_values = seq(0.01, 0.1, by = 0.01)

train.mse = numeric(length(shrinkage_values))
test.mse = numeric(length(shrinkage_values))

for (i in seq_along(shrinkage_values)) {
  set.seed(1)
  boost.fit = gbm(Salary ~ ., data = train.set, distribution = "gaussian",
                     n.trees = 1000, interaction.depth = 1, 
                     shrinkage = shrinkage_values[i])
  
  train.pred = predict(boost.fit, train.set, n.trees = 1000)
  test.pred = predict(boost.fit, test.set, n.trees = 1000)
  train.mse[i] = mean((train.set$Salary - train.pred)^2)
  test.mse[i] = mean((test.set$Salary - test.pred)^2)
}

#plot
plot(shrinkage_values, train.mse, type = "b", col = "blue",
     xlab = "Shrinkage Values", ylab = "Training MSE", main = "Training MSE vs. Shrinkage")

plot(shrinkage_values, test.mse, type = "b", col = "red",
     xlab = "Shrinkage Values", ylab = "Test MSE", main = "Test MSE vs. Shrinkage")

summary(boost.fit)

#compare with lm
lm.fit = lm(Salary~., data = train.set)
lm.pred = predict(lm.fit, newdata = test.set)
lm.mse = mean((test.set$Salary - lm.pred)^2)
lm.mse

summary(lm.fit)
lm.fit2 = lm(Salary~. + Years:Walks, data = train.set)
lm.pred2 = predict(lm.fit2, newdata = test.set)
lm.mse2 = mean((test.set$Salary - lm.pred2)^2)
lm.mse2


#bagging
set.seed(1)
bag.fit = randomForest(Salary ~ ., data = train.set, mtry = ncol(train.set) - 1, 
                          ntree = 500)
bag.pred = predict(bag.fit, test.set)
bag.mse = mean((test.set$Salary - bag.pred)^2)
bag.mse
