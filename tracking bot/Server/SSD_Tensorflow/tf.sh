#!/bin/bash
#This is a shell script to convert Pascal VOC datasets(2007 and 2012) into TF-Records only.
#Directory where the original dataset is stored

#DATASET_DIR=/home/wu/VOCtest_06-Nov-2007/VOCdevkit/VOC2007/
DATASET_DIR=/home/ldu/VOCdevkit/VOC2007/
#Output directory where to store TFRecords files
OUTPUT_DIR=/home/ldu/SSD_Tensorflow/voc2007_test_tfrecords
python3 ./tf_convert_data.py \
    --dataset_name=pascalvoc \
    --dataset_dir=${DATASET_DIR} \
    --output_name=voc_2007_test \
    --output_dir=${OUTPUT_DIR}
