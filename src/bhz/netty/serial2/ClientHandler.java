package bhz.netty.serial2;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

//用于读取客户端发来的信息
public class ClientHandler extends ChannelHandlerAdapter {

	// 客户端与服务端，连接成功的售后
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// 发送消息
		Request request1 = new Request("666");
		Request request2 = new Request("777");
		Request request3 = new Request("888");
		ctx.writeAndFlush(request1);
		ctx.writeAndFlush(request2);
		Thread.sleep(2000);
		ctx.writeAndFlush(request3);
	}

	// 只是读数据，没有写数据的话
	// 需要自己手动的释放的消息
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		try {
			Response response = (Response) msg;
			System.out.println(response);

		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();
	}

}

