library(ISLR2)
Boston
nrow(Boston)
ncol(Boston)
View(Boston)
?Boston

pairs(Boston)

cor(Boston[-1],Boston$crim)

summary(Boston$crim)
summary(Boston$tax)
summary(Boston$ptratio)

sum(Boston$chas)

median(Boston$ptratio)

min_value_index = which.min(Boston$medv)
min_value_index
lowest_tract = Boston[min_value_index, ]
lowest_tract
summary(Boston)


more_than_seven = sum(Boston$rm > 7)
more_than_seven
more_than_eight = sum(Boston$rm > 8)
more_than_eight
