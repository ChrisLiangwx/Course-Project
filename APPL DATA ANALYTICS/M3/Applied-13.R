library(ISLR2)
library(MASS)
library(class)
library(e1071)
attach(Weekly)

summary(Weekly)
pairs(Weekly)
cor(Weekly[,1:8])

logi_fit = glm(Direction ~ . - Year - Today, data = Weekly, family = binomial)
summary(logi_fit)

#logistic
logi_probs = predict(logi_fit, type = "response")
logi_preds = rep("Down", length(logi_probs))
logi_preds[logi_probs > 0.5] = "Up"
table(logi_preds, Direction)

train = (Year < 2009)
test = Weekly[!train,]
test_direction = Direction[!train]
logi_fit2 = glm(Direction ~ Lag2, data = Weekly, family = binomial, subset = train)
logi_probs2 = predict(logi_fit2, test, type = "response")
logi_preds2 = rep("Down", length(logi_probs2))
logi_preds2[logi_probs2 > 0.5] = "Up"
table(logi_preds2, test_direction)
mean(logi_preds2 == test_direction)

#LDA
LDA_fit = lda(Direction ~ Lag2, data = Weekly, subset = train)
LDA_preds = predict(LDA_fit, test)$class
table(LDA_preds, test_direction)
mean(LDA_preds == test_direction)

#QDA
QDA_fit = qda(Direction ~ Lag2, data = Weekly, subset = train)
QDA_preds = predict(QDA_fit, test)$class
table(QDA_preds, test_direction)
mean(QDA_preds == test_direction)

#KNN k = 1
train_X = Weekly[train, "Lag1"]
test_X = Weekly[!train, "Lag1"]
train_Direction = Weekly[train, "Direction"]
set.seed(1)
knn_pred = knn(data.frame(train_X), data.frame(test_X), train_Direction, k = 1)
table(knn_pred, test_direction)
mean(knn_pred == test_direction)

#Naive Bayes
nb_fit = naiveBayes(Direction ~ Lag2, data = Weekly, subset = train)
nb_preds = predict(nb_fit, test)
table(nb_preds, test_direction)
mean(nb_preds == test_direction)


#try combinations
logi_fit3 = glm(Direction ~ Lag2 + I(Lag1^2), data = Weekly,family = binomial, subset = train)
logi_probs3 = predict(logi_fit3, test, type = "response")
logi_preds3 = rep("Down", length(logi_probs3))
logi_preds3[logi_probs3 > 0.5] = "Up"
table(logi_preds3, test_direction)
mean(logi_preds3 == test_direction)
