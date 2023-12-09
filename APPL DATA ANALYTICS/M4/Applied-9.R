library(tree)
library(ISLR2)
library(randomForest)
library(caTools)
library(BART)
attach(OJ)
head(OJ)

#prepare test and train set
set.seed(1)
sample.data = sample.split(OJ$Purchase, SplitRatio = 800/nrow(OJ))
train.set = subset(OJ, sample.data)
test.set = subset(OJ, !sample.data)

#fit tree
tree.fit = tree(Purchase ~ ., data = train.set)
summary(tree.fit)
plot(tree.fit)
text(tree.fit)
tree.fit

#predict
tree.preds = predict(tree.fit, newdata = test.set, type = "class")
table(tree.preds, test.set$Purchase)
(22+25) / (22+25+140+83)

#cv
cv.tree.fit = cv.tree(tree.fit)
plot(cv.tree.fit$size, cv.tree.fit$dev, type = "b")
opt.size = which.min(cv.tree.fit$dev)
pruned.tree = prune.tree(tree.fit, best = cv.tree.fit$size[opt.size])
plot(pruned.tree)
text(pruned.tree, pretty = 0)

#test.set
pruned.preds = predict(pruned.tree, newdata = test.set, type = "class")
table(pruned.preds, test.set$Purchase)
(25+22)/(140+83+25+22)

#train.set
pruned.preds = predict(pruned.tree, newdata = train.set, type = "class")
table(pruned.preds, train.set$Purchase)
(59+72)/(429+240+59+72)
