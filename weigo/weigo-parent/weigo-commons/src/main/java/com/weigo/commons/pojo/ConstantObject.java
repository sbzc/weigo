package com.weigo.commons.pojo;

public class ConstantObject {
   
    //��Ʒ�����Ŀ¼
    public static final Integer itemCarRoot = 2;
    //����id
    public static final Long bigPics = 89l;
    //�����µĹ��    
    public static final Long bigPicBlows = 90l;
    //������Ʒ�Ĺ��
    public static final Long hostItemPicBlows = 96l;
    //������
    public static final Integer resetOrderItem = 0;
    //�û��˿�
    public static final Integer refundOrderItem = 1;
    //�̼�ͬ���˿�
    public static final Integer  refundOrderItemSuccess= 2;
    //�ȴ����Ա�ӵ�
    public static final Integer prepareSendOrderItem = 3;
    //�ͻ���
    public static final Integer SendOrderItem = 4;
    //��ǩ�մ�ȷ���ջ�
    public static final Integer signOnOrderItem=5;
    //ȷ���ջ�
    public  static final Integer finishOrderItem = 6;
    //����״̬����
    public  static final Integer [] orderItemStartArry = {resetOrderItem,refundOrderItem,refundOrderItemSuccess,prepareSendOrderItem,
    		                                              SendOrderItem,signOnOrderItem,finishOrderItem};
    //����״̬���ϣ����ı�ʾ��
    public  static final String [] orderItemStartStrArry = {"������","�����˿�","���˿�","���ӵ�",
    		                                              "������","��ǩ��","�����"};
    //��̨��ҳ
    public  static final Integer managePage=1;
    //ǰ��ҳ
    public  static final Integer PortalPage=2;
    //������ҳ
    public  static final Integer searchPage=3;
    //���ﳵ
    public  static final Integer cartPage=4;
    //��Ʒ����
    public  static final Integer itemdescPage=5;
    //��ݽӵ�ҳ��
    public  static final Integer dispatcherPage=6;
    public  static final String[] visitorPageArray= {"","��̨��ҳ","ǰ̨��ҳ","������ҳ","���ﳵ��ҳ","��Ʒ����ҳ","���ҳ��"};
    
   
    
    
}