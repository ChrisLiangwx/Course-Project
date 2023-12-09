library(ISLR2)
library(MASS)
library(caTools)

set.seed(1)

head(Default)
attach(Default)
#SplitRatio = 0.6
sample.data = sample.split(Default, SplitRatio = 0.6)
train.set = subset(Default, sample.data)
test.set = subset(Default, !sample.data)

lr.fit = glm(default ~ income + balance, data = train.set, family = binomial)

lr.probs = predict(lr.fit, test.set, type = "response")
lr.preds = rep("No", nrow(test.set))
lr.preds[lr.probs > 0.5] = "Yes"
table(lr.preds, test.set$default)
(23+100)/(4827+50+100+23)

#add student to model
lr.fit1 = glm(default ~ income + balance + student, data = train.set, family = binomial)

lr.probs1 = predict(lr.fit, test.set, type = "response")
lr.preds1 = rep("No", nrow(test.set))
lr.preds1[lr.probs1 > 0.5] = "Yes"
table(lr.preds1, test.set$default)



# 
# 
# #SplitRatio = 0.7
# sample.data = sample.split(Default, SplitRatio = 0.7)
# train.set = subset(Default, sample.data)
# test.set = subset(Default, !sample.data)
# 
# lr.fit = glm(default ~ income + balance, data = train.set, family = binomial)
# summary(lr.fit)
# 
# lr.probs = predict(lr.fit, test.set, type = "response")
# lr.preds = rep("No", nrow(test.set))
# lr.preds[lr.probs > 0.5] = "Yes"
# table(lr.preds, test.set$default)
# (106+14)/(4822+48+106+14)
# 
# 
# 
# #SplitRatio = 0.5
# sample.data = sample.split(Default, SplitRatio = 0.5)
# train.set = subset(Default, sample.data)
# test.set = subset(Default, !sample.data)
# 
# lr.fit = glm(default ~ income + balance, data = train.set, family = binomial)
# summary(lr.fit)
# 
# lr.probs = predict(lr.fit, test.set, type = "response")
# lr.preds = rep("No", nrow(test.set))
# lr.preds[lr.probs > 0.5] = "Yes"
# table(lr.preds, test.set$default)
# (27+122)/(4798+53+122+27)
# 
# 
# 
# 
# #SplitRatio = 0.8
# sample.data = sample.split(Default, SplitRatio = 0.8)
# train.set = subset(Default, sample.data)
# test.set = subset(Default, !sample.data)
# 
# lr.fit = glm(default ~ income + balance, data = train.set, family = binomial)
# summary(lr.fit)
# 
# lr.probs = predict(lr.fit, test.set, type = "response")
# lr.preds = rep("No", nrow(test.set))
# lr.preds[lr.probs > 0.5] = "Yes"
# table(lr.preds, test.set$default)
# (53+11)/(2416+20+11+53)

