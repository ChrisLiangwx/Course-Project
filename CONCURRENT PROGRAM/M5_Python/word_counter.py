import re, sys, collections
import concurrent.futures, time

def task_to_run(id : int) -> collections.Counter:
    # record stop words
    stopwords = set(open('stop_words').read().split(','))

    counter = collections.Counter()
    # using parameters in the command line to read files
    filenames = sys.argv[1:]

    words = re.findall('\w{3,}', open(filenames[id]).read().lower())
    counter.update(w for w in words if w not in stopwords)
    return counter


futures = []

with concurrent.futures.ThreadPoolExecutor(max_workers=4) as executor:
    for i in range(4):
        future = executor.submit(task_to_run, i)
        futures.append(future)

total_counter = collections.Counter()

for future in futures:
    total_counter.update(future.result())

for (w, c) in total_counter.most_common(40):
    print(w, ':', c)

