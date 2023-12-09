set.seed(1) 
class1 = matrix(rnorm(20*50, mean = 0), ncol = 50)
class2 = matrix(rnorm(20*50, mean = 1), ncol = 50)
class3 = matrix(rnorm(20*50, mean = 2), ncol = 50)
data = rbind(class1, class2, class3)
dim(data)

pca = prcomp(data)
plot(pca$x[,1:2], col = rep(1:3, each = 20), pch = 19, xlab = "PC1", ylab = "PC2")

#k-means k = 3
km = kmeans(data, centers = 3, nstart = 20)
table(km$cluster, rep(1:3, each = 20))


#k-means k = 2
km2 = kmeans(data, centers = 2, nstart = 20)
table(km2$cluster, rep(1:3, each = 20))


#k-means k = 4
km3 = kmeans(data, centers = 4, nstart = 20)
table(km3$cluster, rep(1:3, each = 20))

#pca
km.pca = kmeans(pca$x[,1:2], centers = 3, nstart = 20)
table(km.pca$cluster, rep(1:3, each = 20))

#scaling
scaled.data = scale(data)
km.scaled = kmeans(scaled.data, centers = 3, nstart = 20)
table(km.scaled$cluster, rep(1:3, each = 20))

