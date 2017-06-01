import cloudinary
import cloudinary.uploader
import cloudinary.api
import urllib

downloadDir="/Users/shrem/Movies/design/"
roomOffering=['','','','','']
projectId = 3
supplierId=1
# aptIds=[1,2,3,4]
aptIds=[1]
livingRoomOffering=[5,6,13,14]
bedroomOffering=[5,6,13,14]
bathroomOffering=[10,11,12,15]
kitchenOffering=[21,22,23,24]
roomIds=[2,1,3,4]
cloudinaryPath = "projects/shikunbinuidpchk/";
cloudinaryPath2 = "video:projects:shikunbinuidpchk:";

def getVideoValue(aptId, supplierId, roomId, offeringId):
	roomOffering[roomId] = str(offeringId);
	# kitchen - living room and kitchen in the same video
	if roomId == 4:
		return "_" + str(roomId) + "_" + str(offeringId);
	

	path = cloudinaryPath2 +  str(aptId) + ":";
	# living room - the first video
	if roomId == 2:  
		path = cloudinaryPath + str(aptId) + "/";
		
	
	path = path + str(supplierId) + "_" + str(roomId) + "_" + str(offeringId);

	return path;

def downloadVideo (videoFile1,videoFile2,videoFile3):
	print(videoFile1)
	print(videoFile2)
	print(videoFile3)
	videoUrl = cloudinary.utils.cloudinary_url(videoFile1, resource_type= 'video', format= 'mp4', transformation=[{'overlay': videoFile2, 'flags': "splice"}, {'overlay': videoFile3, 'flags': "splice"}])
	print(videoUrl[0])
	video =urllib.FancyURLopener()
	video.retrieve(videoUrl[0], downloadDir + '1.mp4')

for aptIdx, apt in enumerate(aptIds):
	for lRoom in livingRoomOffering:
		for bRoom in bedroomOffering:
			for bathRoom in bathroomOffering:
				for kRoom in kitchenOffering:
					print('{}, {}, {}, {}, {}\n'.format(lRoom, bRoom, bathRoom, kRoom))
					videoFile1 = getVideoValue(apt,supplierId,2,lRoom) + getVideoValue(apt,supplierId,4,kRoom)
					videoFile2 = getVideoValue(apt,supplierId,1,bRoom)
					videoFile3 = getVideoValue(apt,supplierId,3,bathRoom)
					downloadVideo(videoFile1,videoFile2,videoFile3)



