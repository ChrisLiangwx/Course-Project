library(tree)
library(ISLR2)
library(randomForest)
head(Boston)

set.seed(1)
train = sample(1:nrow(Boston), nrow(Boston) / 2)
test = Boston$medv[-train]

bag.boston = randomForest(medv ~ ., data = Boston, subset = train, mtry = 16, ntree = 500, importance = TRUE)
bag.boston
yhat.bag = predict(bag.boston, newdata = Boston[-train, ])
plot(yhat.bag, test)
abline(0, 1)
mean((yhat.bag - test)^2)

bag.boston = randomForest(medv ~ ., data = Boston, subset = train, mtry = 12, ntree = 500, importance = TRUE)
bag.boston
yhat.bag = predict(bag.boston, newdata = Boston[-train, ])
plot(yhat.bag, test)
abline(0, 1)
mean((yhat.bag - test)^2)


bag.boston = randomForest(medv ~ ., data = Boston, subset = train, mtry = 6, ntree = 500, importance = TRUE)
bag.boston
yhat.bag = predict(bag.boston, newdata = Boston[-train, ])
plot(yhat.bag, test)
abline(0, 1)
mean((yhat.bag - test)^2)

bag.boston = randomForest(medv ~ ., data = Boston, subset = train, mtry = 6, ntree = 25, importance = TRUE)
bag.boston
yhat.bag = predict(bag.boston, newdata = Boston[-train, ])
plot(yhat.bag, test)
abline(0, 1)
mean((yhat.bag - test)^2)

bag.boston = randomForest(medv ~ ., data = Boston, subset = train, mtry = 6, ntree = 5000, importance = TRUE)
bag.boston
yhat.bag <- predict(bag.boston, newdata = Boston[-train, ])
plot(yhat.bag, test)
abline(0, 1)
mean((yhat.bag - test)^2)

