scaled.data = scale(USArrests)

cor.dis = as.dist((1 - cor(t(scaled.data)))/2)
euc.dis = dist(scaled.data)

ratio = cor.dis / (euc.dis^2)
mean(ratio)
sd(ratio)
