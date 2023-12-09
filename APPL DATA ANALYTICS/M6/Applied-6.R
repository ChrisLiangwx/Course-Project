library(ISLR2)
library(MASS)
library(caTools)
library(boot)
set.seed(1)


head(Default)
attach(Default)

#logistic regression
lr.fit = glm(default ~ income + balance, data = train.set, family = binomial)
summary(lr.fit)$coefficient

#boot.fn
boot.fn = function(data, index){
  lr.fit = glm(default~income + balance, data = Default, subset = index, family = binomial)
  return(coef(lr.fit))
}

boot.fn(Default, 1:nrow(Default))

#boot
boot.fit = boot(Default, boot.fn, R = 1000)
boot.fit


