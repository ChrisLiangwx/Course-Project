library(ISLR2)
library(MASS)
library(class)
library(e1071)

attach(Boston)
summary(Boston)
pairs(Boston)
numeric_columns = sapply(Boston, is.numeric)
cor(Boston$crim, Boston[, numeric_columns])

median_crim = median(Boston$crim)
Boston$HighCrime = as.factor(ifelse(Boston$crim > median_crim, "Above", "Below"))
set.seed(1)
train_index = sample(1:nrow(Boston), 0.7 * nrow(Boston))
train_data = Boston[train_index, ]
test_data = Boston[-train_index, ]

#logistic regression
logi_model = glm(HighCrime ~. - chas - crim, data = train_data, family = "binomial")
logi_preds = predict(logi_model, test_data, type = "response")
logi_preds = ifelse(logi_preds > 0.5, "Above", "Below")
table(logi_preds, test_data$HighCrime)
mean(logi_preds == test_data$HighCrime)

#LDA
lda_model = lda(HighCrime ~. - chas - crim, data = train_data)
lda_preds = predict(lda_model, test_data)$class
table(lda_preds, test_data$HighCrime)
mean(lda_preds == test_data$HighCrime)

#QDA
qda_model = qda(HighCrime ~.- chas - crim, data = train_data)
qda_preds = predict(qda_model, test_data)$class
table(qda_preds, test_data$HighCrime)
mean(qda_preds == test_data$HighCrime)

#Naive Bayes
nb_model = naiveBayes(HighCrime ~.- chas - crim, data = train_data)
nb_preds = predict(nb_model, test_data)
table(nb_preds, test_data$HighCrime)
mean(nb_preds == test_data$HighCrime)

#KNN
train_data_knn = train_data[, !(names(train_data) %in% c("crim", "chas"))]
test_data_knn = test_data[, !(names(test_data) %in% c("crim", "chas"))]
knn_preds = knn(train_data_knn[ , -which(names(train_data_knn) == "HighCrime")], 
                 test_data_knn[ , -which(names(test_data_knn) == "HighCrime")], 
                 train_data_knn$HighCrime, k = 1)
table(knn_preds, test_data$HighCrime)
mean(knn_preds == test_data$HighCrime)

knn_preds = knn(train_data_knn[ , -which(names(train_data_knn) == "HighCrime")], 
                 test_data_knn[ , -which(names(test_data_knn) == "HighCrime")], 
                 train_data_knn$HighCrime, k = 3)
table(knn_preds, test_data$HighCrime)
mean(knn_preds == test_data$HighCrime)

knn_preds = knn(train_data_knn[ , -which(names(train_data_knn) == "HighCrime")], 
                 test_data_knn[ , -which(names(test_data_knn) == "HighCrime")], 
                 train_data_knn$HighCrime, k = 5)
table(knn_preds, test_data$HighCrime)
mean(knn_preds == test_data$HighCrime)