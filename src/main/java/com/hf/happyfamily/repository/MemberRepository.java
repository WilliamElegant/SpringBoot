package com.hf.happyfamily.repository;

import com.hf.happyfamily.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link Member } {@link Repository}
 */

@Repository
public class MemberRepository {

    /**
     * 采用内存型的存储方式
     */
    private final ConcurrentMap<Integer, Member> repository = new ConcurrentHashMap<>();

    private final static AtomicInteger idGenerator = new AtomicInteger();
    /**
     * 保存家庭成员对象
     * @param member {@link Member} 对象
     * @return 如果保存成功，返回<code>true</code>,
     *          否则，返回<code>false</code>
     */
    public boolean save(Member member){
        // ID 从1 开始
        Integer id = idGenerator.incrementAndGet();
        member.setId(id);
        return repository.put(id, member) == null;
    }

    public Collection<Member> findAll(){
        return repository.values();
    }
}
