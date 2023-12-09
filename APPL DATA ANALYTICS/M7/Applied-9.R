set.seed(1)

method.complete = hclust(dist(USArrests), method = 'complete')
plot(method.complete)

#cut at height 3
method.complete.cut = cutree(method.complete, k = 3)
method.complete.cut

#scale
scaled.data = scale(USArrests)
method.complete.scaled = hclust(dist(scaled.data), method = 'complete')
plot(method.complete.scaled)

#compare
par(mfrow = c(1, 2)) 
plot(method.complete, main = "Complete Linkage without Scaling")
plot(method.complete.scaled, main = "Complete Linkage with Scaling")
