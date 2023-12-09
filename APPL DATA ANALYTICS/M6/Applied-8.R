library(ISLR2)
library(MASS)
library(caTools)
library(boot)

# set.seed(2)
set.seed(1)
x = rnorm(100)
y = x-2*x^2+rnorm(100)
plot(x,y)



#model with different degrees
data = data.frame(x,y)
#i
model_i = glm(y ~ x, data = data)
cv_i = cv.glm(data, model_i)
cv_error_i = cv_i$delta[1]

#ii
data$x2 = x^2
model_ii = glm(y ~ x + x2, data = data)
cv_ii = cv.glm(data, model_ii)
cv_error_ii = cv_ii$delta[1]

#iii
data$x3 = x^3
model_iii = glm(y ~ x + x2 + x3, data = data)
cv_iii = cv.glm(data, model_iii)
cv_error_iii = cv_iii$delta[1]

#iv
data$x4 = x^4
model_iv = glm(y ~ x + x2 + x3 + x4, data = data)
cv_iv = cv.glm(data, model_iv)
cv_error_iv = cv_iv$delta[1]

cv_error_i
cv_error_ii
cv_error_iii
cv_error_iv


summary(model_i)
summary(model_ii)
summary(model_iii)
summary(model_iv)
