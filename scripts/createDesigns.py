projectId = 3
aptCode=['A1','A2','B1','B2']
designTitle='zehavi aztmon'
designerId=2
designImgCode='Zehavi_IC_IC_CCVM'
livingRoomOffering=[5,6,13,14]
bedroomOffering=[5,6,13,14]
bathroomOffering=[10,11,12,15]
kitchenOffering=[21,22,23,24]
roomIds=[2,1,3,4]
facebookUrl='https://www.facebook.com/1848414988734528/videos/1848855945357099/'
tab='\t'
fileName = "/Users/shrem/dev/ds/designs.tsv"
f = open(fileName, 'w')

f.write('ProjectId\tAptTmplCode\tDesignTitle\tDesignerId\tDesignImgCode\tOffering\tRoom\n')
for apt in aptCode:
	for lRoom in livingRoomOffering:
		for bRoom in bedroomOffering:
			for bathRoom in bathroomOffering:
				for kRoom in kitchenOffering:
					f.write('{}\t{}\t{}\t{}\t{}\t{}\t2\t{}\n'.format(projectId, apt, designTitle, designerId, designImgCode, lRoom, facebookUrl))
					f.write('\t\t\t\t\t{}\t1\t{}\n'.format(bRoom, facebookUrl))
					f.write('\t\t\t\t\t{}\t3\t{}\n'.format(bathRoom, facebookUrl))
					f.write('\t\t\t\t\t{}\t4\t{}\n'.format(kRoom, facebookUrl))