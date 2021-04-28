import tensorflow as tf
import datetime
from tensorflow.examples.tutorials.mnist import input_data

def weight_variable(shape):#权重正态分布初始化
    initial=tf.truncated_normal(shape,stddev=0.1)
    return tf.Variable(initial)
# tf.truncated_normal(shape, mean, stddev) :shape表示生成张量的维度，mean是均值，stddev是标准差。
def bias_variable(shape):#偏置量初始化
    initial=tf.constant(0.1,shape=shape)#value=0.1,shape是生成的维度
    return tf.Variable(initial)
def conv2d(x,W):#定义2维的卷积图层
    return  tf.nn.conv2d(x, W, strides=[1,1,1,1], padding='SAME')
# strides：每跨多少步抽取信息，strides[1, x_movement,y_movement, 1]， [0]和strides[3]必须为1
# padding：边距处理，“SAME”表示输出图层和输入图层大小保持不变，设置为“VALID”时表示舍弃多余边距(丢失信息)
def max_pool(x):
    return tf.nn.max_pool(x, ksize=[1,2,2,1], strides=[1,2,2,1], padding='SAME')
# ksize池化窗口的大小一般是[1, height, width, 1]，所以这两个维度设为了1
# strides和卷积类似，窗口在每一个维度上滑动的步长，一般也是[1, stride,stride, 1]


if __name__ == '__main__':
    x= tf.placeholder(tf.float32, [None,784])
    # 输入数据 None表示行不定
    x_image = tf.reshape(x, [-1,28,28,1]) 
    # 将原图reshape为4维，-1表示数据是黑白的，28*28=784，1表示颜色通道数目
    y= tf.placeholder(tf.float32, [None,10])
    W_conv1 = weight_variable([5, 5, 1, 32])
    # 按照[5,5,输入通道=1,输出通道=32]生成一组随机变量
    b_conv1 = bias_variable([32])                               
    h_conv1 = tf.nn.relu(conv2d(x_image, W_conv1) + b_conv1)    
    # 输出size 28*28*32(因为conv2d()中x和y步长都为1，边距保持不变)
    h_pool1 = max_pool(h_conv1)
    # 把h_pool1的厚度由32增加到64，长宽由14*14缩小为7*7
    W_conv2 = weight_variable([5, 5, 32, 64]) 
    b_conv2 = bias_variable([64])
    h_conv2 = tf.nn.relu(conv2d(h_pool1, W_conv2) + b_conv2)
    h_pool2 = max_pool(h_conv2)
    ### 3. 第一层全连接
    # 把h_pool2由7*7*64，变成1024*1
    W_fc1 = weight_variable([7*7*64, 1024])
    b_fc1 = bias_variable([1024])
    h_pool2_flat = tf.reshape(h_pool2, [-1, 7*7*64])
    # 把pooling后的结构reshape为一维向量
    h_fc1 = tf.nn.relu(tf.matmul(h_pool2_flat, W_fc1) + b_fc1)
    keep_prob = tf.placeholder(tf.float32)
    h_fc1_drop = tf.nn.dropout(h_fc1, keep_prob)            
    # 按照keep_prob的概率扔掉一些，为了减少过拟合 
    W_fc2 = weight_variable([1024, 10])
    b_fc2 = bias_variable([10])
    predict=tf.add(tf.matmul(h_fc1_drop, W_fc2) , b_fc2)
    y_conv = tf.nn.softmax(tf.matmul(h_fc1_drop, W_fc2) + b_fc2)
    cross_entropy=tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(logits=predict, labels=y))#计算误差
    train_step = tf.train.AdamOptimizer(1e-4).minimize(cross_entropy)#通过使用动量（参数的移动平均数）来改善传统梯度下降，促进超参数动态调整
    correct_prediction = tf.equal(tf.argmax(y_conv, 1), tf.argmax(y, 1))#找出预测正确的标签
    accuracy = tf.reduce_mean(tf.cast(correct_prediction, 'float'))#得出通过正确个数除以总数得出准确率

    with tf.Session() as sess:
        sess.run(tf.global_variables_initializer())
        starttime = datetime.datetime.now()
        print("start: " + str(starttime))
        mnist = input_data.read_data_sets('MNIST_data/', one_hot=True)
        for i in range(1000):
            batch = mnist.train.next_batch(100)
            if i % 50 == 0:
                cross_entropy_now = sess.run(cross_entropy,feed_dict={x: batch[0], y: batch[1], keep_prob: 1})
                print( 'step %d, training error %g' %(i, cross_entropy_now))
            sess.run(train_step, feed_dict = {x: batch[0], y: batch[1], keep_prob: 0.8})
        print ('test accuracy %g' % accuracy.eval(session = sess, feed_dict={x: mnist.test.images, y: mnist.test.labels, keep_prob: 1.0}))

