import re, sys, collections

# record stop words
stopwords = set(open('stop_words').read().split(','))

total_counts = collections.Counter()

# using parameters in the command line to read files
filenames = sys.argv[1:]

for filename in filenames:
    words = re.findall('\w{3,}', open(filename).read().lower())
    total_counts.update(w for w in words if w not in stopwords)


for (w, c) in total_counts.most_common(40):
    print(w, ':', c)

